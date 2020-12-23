import React from 'react';
import './ChatMessageField.css';

class ChatMessageField extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            value: '',
            userName: 'defaultUser'
        }

        this.handleTextChange = this.handleTextChange.bind(this);
        this.send = this.send.bind(this);
        this.handleUserNameChange = this.handleUserNameChange.bind(this);
    }

    send() {
        const message = {
            userName: this.state.userName,
            content: this.state.value
        }
        this.props.webSocket.send(JSON.stringify(message))
    }

    handleTextChange(event) {
        this.setState({
            userName: this.state.userName,
            value: event.target.value
        });
    }

    handleUserNameChange(event) {
        this.setState({
            userName: event.target.value,
            value: this.state.value
        });
    }

    render() {
        return (
            <div>
                <div className="row chat-message-field">
                    <textarea className="form-control chat-text-field" value={this.state.value} onChange={this.handleTextChange}></textarea>
                    <button className="btn btn-light btn-block" onClick={this.send}>Send</button>
                </div>
                <div className="row input-group">
                    <span className="input-group-text" id="basic-addon1">UserName</span>
                    <input type="text" className="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" value={this.state.userName} onChange={this.handleUserNameChange} />
                </div>
            </div>
        );
    }
}

export default ChatMessageField