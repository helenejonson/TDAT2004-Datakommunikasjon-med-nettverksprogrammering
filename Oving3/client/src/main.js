import React, {Component} from "react";
import "./main.css"
import {service} from "./service";

export class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {code: "", show: false}
    }

    myState(input){
        service.getCode(input)
            .then(out =>
                this.setState({
                    code: out.stdout + out.stderr,
                    show: true
                })
            )
            .catch((error) => {
                console.error(error);
            });
    }

    render(){
        return(
            <div id="main">
                <h1>Øving 3</h1>
                <div id="input">
                    <div className="form-group">
                        <h5>main.cpp</h5>
                        <textarea className="form-control" id="codeInput" rows="9" placeholder="Your amazing code"/>
                    </div>
                </div>
                <div id="btn">
                    <button type="button" className="btn btn-outline-dark" onClick={() => this.myClick()}>Compile and run</button>
                </div>
                <div id="output" >
                    <h5>Output:</h5>
                    {this.state.show && <pre><code> {this.state.code}</code></pre>}
                </div>

            </div>
        );
    }

    myClick(){
        console.log("I am totally compiling now");
        const input = document.getElementById("codeInput").value;
        console.log(input);
        this.myState(input)

    }

}
