import { Model } from "@vuex-orm/core"
import Company from "./Company"

export default class Recruiter extends Model {

    static entity = 'recruiter'

    static fields() {
        return {
            id: this.number(null),
            firstName: this.string(''),
            lastName: this.string(''),
            email: this.string(''),
            phone: this.string(''),
            company_id: this.number(null),
            company: this.belongsTo(Company, 'company_id')
        }
    }
}