import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import ChatMessageFiled from './ChatMessageField.js'
import jest from 'jest-mock';

describe("ChatMessageField component", () => {

    it("should fill in default username", () => {
        let result = render(<ChatMessageFiled />)

        expect(result.container.querySelector('#usernameInput').value).toEqual("defaultUser");
    });

    it("should be able to change userName", () => {
        let result = render(<ChatMessageFiled />);
        const inputField = result.container.querySelector('#usernameInput');

        fireEvent.change(inputField, { target: { value: 'newUser' } })
        expect(inputField.value).toBe('newUser')
    });

    it("should send message to websocket when send button is clicked", async () => {
        const send = jest.fn()
        const webSocketMock = {
            send : send
        }
        const result = render(<ChatMessageFiled webSocket={webSocketMock} />)

        const textMessageField = result.container.querySelector('#messageInput');
        fireEvent.change(textMessageField, { target: { value: 'testMessage' } })

        const sendButton = result.container.querySelector('#sendButton');
        fireEvent.click(sendButton)

        expect(send).toBeCalledTimes(1)
        expect(send.mock.calls[0][0]).toEqual("{\"userName\":\"defaultUser\",\"content\":\"testMessage\"}")
    });
})