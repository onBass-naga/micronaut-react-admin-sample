import React from 'react'
import { Admin, Resource } from 'react-admin'
import fakeDataProvider from 'ra-data-fakerest'
import { TaskList } from './tasks/TaskList'
import tasks from './mock-data/tasks'

console.log(tasks)
const dataProvider = fakeDataProvider(tasks)

const App = () => (
  <Admin dataProvider={dataProvider}>
    <Resource name="tasks" list={TaskList} />
  </Admin>
)

export default App
