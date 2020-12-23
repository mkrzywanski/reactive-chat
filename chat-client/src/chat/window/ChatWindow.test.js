import React from 'react';
import { render, screen } from '@testing-library/react';
import ChatWindow from './ChatWindow.js'

describe("ChatWindow component", () => {
    it("should display messages", () => {
        const messages = [{
            id : "11",
            content : 'testMessage',
            userName : 'user'
        }];

        const result = render(<ChatWindow messages={messages}/>)

        expect(screen.getByText('testMessage')).not.toBeNull()
        expect(screen.getByText('user', {exact : false})).not.toBeNull()
    })
})