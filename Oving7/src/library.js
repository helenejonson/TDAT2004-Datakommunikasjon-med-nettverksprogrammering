'use strict';
const sha1 = require("crypto-js/sha1");
const Base64 = require("crypto-js/enc-base64");

module.exports.handshake = function handshake(data) {
    let dat = data.toString().split("\n");
    let key = "";
    dat.map(header => {
        if (header.indexOf("Sec-WebSocket-Key:") > -1){
            let indexStart = header.indexOf("Key:");
            key = header.substring(indexStart+4).trim();

        }
    });
    let hash = sha1(key+"258EAFA5-E914-47DA-95CA-C5AB0DC85B11");

    let encoded = Base64.stringify(hash);
    console.log(encoded)

    return encoded;

}




module.exports.datapacks = function datapacks(data) {
    let bytes = Buffer.from(data)
    let length = bytes[1] & 127;
    let maskStart = 2;
    let dataStart = maskStart + 4;
    let mess = "";
    for (let i = dataStart; i < dataStart + length; i++) {
        let byte = bytes[i] ^ bytes[maskStart + ((i - dataStart) % 4)];
        console.log(String.fromCharCode(byte));
        mess += String.fromCharCode(byte);
    }
    console.log(mess);
    mess = "Heisann";
    let returnData = [];
    let type = 0x81;
    returnData.push(type);
    let dataLength = mess.length;
    let maskedAndLength = 0b00000000 | dataLength;
    returnData.push(maskedAndLength);
    let payload = Buffer.from(mess);
    for (let i = 0; i < payload.length; i++) {
        returnData.push(payload[i]);
    }
    let retData = Buffer.from(returnData);
    console.log(retData);
    return retData;



}

module.exports.returnData = function returnData() {
    let mess = "Heisann";
    let returnData = [];
    let type = 0x81;
    returnData.push(type);
    let dataLength = mess.length;
    let maskedAndLength = 0b00000000 | dataLength;
    returnData.push(maskedAndLength);
    let payload = Buffer.from(mess);
    for (let i = 0; i < payload.length; i++) {
        returnData.push(payload[i]);
    }
    let retData = Buffer.from(returnData);
    console.log(retData);
    return retData;

}