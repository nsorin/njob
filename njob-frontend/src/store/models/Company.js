import { Model } from "@vuex-orm/core"
import Application from "./Application"
import Recruiter from "./Recruiter"

export default class Company extends Model {

    static entity = 'company'

    static apiConfig = {
        actions: {
            fetch: {
                method: 'get',
                url: '/companies'
            }
        }
    }

    static fields() {
        return {
            id: this.number(null),
            name: this.string(''),
            address: this.string(''),
            city: this.string(''),
            zipCode: this.number(null),
            webSite: this.string(''),
            applications: this.hasMany(Application, 'company_id'),
            recruiters: this.hasMany(Recruiter, 'company_id')
        }
    }
}