import React from 'react';
import { render, screen } from '@testing-library/react';
import ChatApp from './ChatApp.js'
import WS from "jest-websocket-mock";

describe("ChattApp component", () => {
    it("should display application name", () => {
        const result = render(<ChatApp />)

        expect(screen.getByText('Chatter')).not.toBeNull()
    })

    it("should display message when it is sent by server", async () => {
        const server = new WS(process.env.REACT_APP_WEBSOCKET_URL);
        render(<ChatApp />)
        
        await server.connected;
        
        const message = {
            userName : 'testUser',
            id : 'id',
            content : 'content'
        }

        server.send(JSON.stringify(message))
        
        expect(screen.getByText('content')).not.toBeNull()
    })
})