import { Model } from "@vuex-orm/core"
import Company from "./Company"
import Document from "./Document"

export default class Application extends Model {

    static entity = 'application'

    static fields() {
        return {
            id: this.number(null),
            name: this.string(''),
            description: this.string(''),
            priority: this.number(1),
            company_id: this.number(null),
            company: this.belongsTo(Company, 'company_id'),
            documents: this.hasMany(Document, 'application_id')
        }
    }

}