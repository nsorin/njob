export default function(uri) {
    return (process.env.BACKEND_BASE_URL || 'http://localhost') + "/files/" + uri
}