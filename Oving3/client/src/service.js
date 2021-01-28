import axios from "axios";

class Service{
    getCode(MyCode){
        console.log(MyCode);
        return axios.post("http://localhost:8080/run",{code: MyCode}).then(response => response.data);
    }
}

export let service = new Service();

