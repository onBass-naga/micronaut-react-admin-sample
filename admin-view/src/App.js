import React from 'react'
import { Admin, Resource } from 'react-admin'
import { TaskList, TaskCreate, TaskEdit } from './components/tasks'
import dataProvider from './dataProvider'
import authProvider from './authProvider'

// import fakeDataProvider from 'ra-data-fakerest'
// import tasks from './mock-data/tasks'
// const dataProvider = fakeDataProvider(tasks)

const App = () => (
  <Admin dataProvider={dataProvider} authProvider={authProvider}>
    <Resource
      name="tasks"
      list={TaskList}
      create={TaskCreate}
      edit={TaskEdit}
    />
  </Admin>
)

export default App
