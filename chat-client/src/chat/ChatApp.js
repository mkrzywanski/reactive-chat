import React from 'react';
import './ChatApp.css';
import ChatMessageField from './message-field/ChatMessageField';
import ChatWindow from './window/ChatWindow';

class ChatApp extends React.Component {

    clientWebSocket = new WebSocket(process.env.REACT_APP_WEBSOCKET_URL);

    constructor(props) {
        super(props)
        this.state = {
            messages : Array(0)
        }
    }

    componentDidMount() {

        document.title = 'Chatter'

        this.clientWebSocket.onopen = function () {
            console.log("clientWebSocket.onopen");
        }
        this.clientWebSocket.onclose = function (error) {
            console.log("clientWebSocket.onclose", error);
        }
        this.clientWebSocket.onerror = function (error) {
            console.log("clientWebSocket.onerror", error);
        }
        this.clientWebSocket.onmessage = (event) => {
            console.log(this.state.messages)
            let messagesCopy = this.state.messages.slice();
            messagesCopy.push(JSON.parse(event.data))
            this.setState({
                messages : messagesCopy
            })
        }
        
        
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Chatter</h1>
                <div className="container chat-app">
                    <ChatWindow messages={this.state.messages}/>
                    <ChatMessageField webSocket={this.clientWebSocket}/>
                </div>
            </div>
        );
    }
}

export default ChatApp