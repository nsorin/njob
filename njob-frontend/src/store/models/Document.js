import { Model } from "@vuex-orm/core"
import Application from "./Application"

export default class Document extends Model {

    static entity = 'document'

    static fields() {
        return {
            id: this.number(null),
            name: this.string(''),
            fileName: this.string(''),
            fileType: this.string(''),
            fileKey: this.string(''),
            application_id: this.number(null),
            application: this.belongsTo(Application, 'application_id')
        }
    }
}