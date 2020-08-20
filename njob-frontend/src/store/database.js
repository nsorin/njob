import VuexORM from '@vuex-orm/core'
import Company from './models/Company'
import Application from './models/Application'
import Recruiter from './models/Recruiter'
import Document from './models/Document'

const database = new VuexORM.Database()

database.register(Company)
database.register(Application)
database.register(Recruiter)
database.register(Document)

export default database