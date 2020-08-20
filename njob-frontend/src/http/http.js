import axios from 'axios'

const http = axios.create({
    baseURL: (process.env.BACKEND_BASE_URL || 'http://localhost') + '/api/'
})

export default http