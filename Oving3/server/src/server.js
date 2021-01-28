//import * as child_process from "pn/child_process";

var express = require("express");
var bodyParser = require("body-parser");
var fs = require("file-system");
var app = express();
const child_process = require("child_process");

app.use(express.json());
app.use(bodyParser.json());
app.use(function(req, res, next) {
    res.header('Access-Control-Allow-Origin', 'http://localhost:3000'); // update to match the domain you will make the request from
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
    next();
});



app.post("/run", (req, res) => {
    console.log("se hvor lagt jeg kom");
    console.log(req.body.code);
    fs.writeFileSync("MyFile.cpp", req.body.code);
    child_process.exec('mv MyFile.cpp myFolder', (error,stdout,stderr) => {
        console.log(stdout);
        console.log(stderr);
        child_process.exec('docker container run --rm -v "$(pwd)/myFolder":/myFolder python-image /bin/sh -c "g++ myFolder/MyFile.cpp && ./a.out"', (error, stdout, stderr) => {
            console.log(stdout) && console.log(stderr);
            res.json({
                stdout,
                stderr
            })
        })
    })
    //child_process.exec('docker run --rm python-image python -c \'' + req.body.code +'\'', (error, stdout, stderr) => {

});

let server = app.listen(8080);