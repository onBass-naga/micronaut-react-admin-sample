import React from 'react'
import {
  List,
  Datagrid,
  TextField,
  DateField,
  BooleanField,
  Create,
  Edit,
  SimpleForm,
  TextInput,
  DateTimeInput,
  BooleanInput,
  DisabledInput
} from 'react-admin'

export const TaskList = props => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="overview" />
      <DateField
        source="deadline"
        locales="ja-JP"
        options={{
          year: 'numeric',
          month: '2-digit',
          day: '2-digit',
          hour: '2-digit',
          minute: '2-digit'
        }}
      />
      <BooleanField source="isDone" />
    </Datagrid>
  </List>
)

const TaskTitle = ({ record }) => {
  return <span>Task {record ? `"${record.overview}"` : ''}</span>
}

export const TaskEdit = props => (
  <Edit title={<TaskTitle />} {...props}>
    <SimpleForm>
      <DisabledInput source="id" />
      <TextInput source="overview" />
      <DateTimeInput source="deadline" />
      <BooleanInput source="isDone" />
    </SimpleForm>
  </Edit>
)

export const TaskCreate = props => (
  <Create {...props}>
    <SimpleForm>
      <TextInput source="overview" />
      <DateTimeInput source="deadline" />
    </SimpleForm>
  </Create>
)
