'use strict';
//const Buffer = require("buffer");

const sha1 = require("crypto-js/sha1");
const Base64 = require("crypto-js/enc-base64");
const lib = require("./library");



const net = require('net');

// Simple HTTP server responds with a simple WebSocket client test
const httpServer = net.createServer(connection => {
    connection.on('data', () => {
        let content = `<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
  </head>
  <body>
    WebSocket test page
    <script>
      let ws = new WebSocket('ws://localhost:3001');
      console.log(ws);
      ws.onmessage = event => alert('Message from server: ' + event.data);
      ws.onopen = () => ws.send('hello');
    </script>
  </body>
</html>
`;
        connection.write('HTTP/1.1 200 OK\r\nContent-Length: ' + content.length + '\r\n\r\n' + content);
    });
});
httpServer.listen(3000, () => {
    console.log('HTTP server listening on port 3000');
});

// Incomplete WebSocket server
let connections = [];
const wsServer = net.createServer(connection => {
    console.log('Client connected');
    connections.push(connection);

    connection.on('data', data => {
        console.log('Data received from client: ', data.toString());

        //Put headers into array
        let dat = data.toString().split("\n");

        if(data.toString().includes("GET / HTTP/1.1")) {
            let encode = lib.handshake(data);
            connection.write('HTTP/1.1 101 Switching Protocols\r\n'
                + 'Upgrade: websocket\r\n' +
                'Connection: Upgrade\r\n'
                + 'Sec-WebSocket-Accept: ' + encode.trim() + '\r\n' + '\r\n');
        }else {
            console.log('Data received from client: ', data.toString());


            if(Buffer.from(data)[0] === 136){
                console.log(connections.length)
                let index = connections.indexOf(connection)
                connections.splice(index, 1)
                console.log(connections.length)

            }else{
                let retData = lib.datapacks(data);
                for(let i = 0; i < connections.length; i++){
                    connections[i].write(retData);
                }
            }
        }
    });




    connection.on('end', () => {
        console.log('Client disconnected');
    });
});

wsServer.on('error', error => {
    console.error('Error: ', error);
});
wsServer.listen(3001, () => {
    console.log('WebSocket server listening on port 3001');
});
