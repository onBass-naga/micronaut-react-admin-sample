import React from 'react'
import { List, Datagrid, TextField, BooleanField } from 'react-admin'

export const TaskList = props => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="overview" />
      <TextField source="deadline" />
      <BooleanField source="isDone" />
    </Datagrid>
  </List>
)
