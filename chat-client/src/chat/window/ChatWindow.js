import React from 'react';
import './ChatWindow.css';

class ChatApp extends React.Component {
    
    render() {
        console.log(this.props.messages)
        const listItems = this.props.messages.map((msg) => 
            <div className="text-break" key={msg.id}>
                <div className="row">
                    {msg.userName} :
                </div>
                <div className="row chat-message">
                    {msg.content}
                </div>
            </div>
        );
        return (
            <div className="row overflow-auto chat-window">
                <div className="col-sm">{listItems}</div>
            </div>
        );
    }
}

export default ChatApp