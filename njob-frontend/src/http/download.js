import download from 'downloadjs'
import axios from 'axios'

const http = axios.create({
    baseURL: (process.env.BACKEND_BASE_URL || 'http://localhost') + '/files/'
})

export default {
    
    get(uri, name) {
        return http.get(uri).then((response) => {
            const content = response.headers['content-type'];
            download(new Blob([response.data]), name, content)
        }).catch(error => console.log(error))
    }
}