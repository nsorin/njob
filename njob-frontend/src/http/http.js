import axios from 'axios'

const http = axios.create({
    baseURL: process.env.BACKED_BASE_URL || 'http://localhost:8000'
})

export default http