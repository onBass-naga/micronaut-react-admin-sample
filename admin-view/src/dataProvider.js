import {
  GET_LIST,
  GET_ONE,
  GET_MANY,
  GET_MANY_REFERENCE,
  CREATE,
  UPDATE,
  DELETE,
  DELETE_MANY,
  fetchUtils
} from 'react-admin'
import { stringify } from 'qs'

const API_URL = 'http://localhost:8086'

const convertDataProviderRequestToHTTP = (type, resource, params) => {
  switch (type) {
    case GET_LIST: {
      const { page, perPage } = params.pagination
      const { field, order } = params.sort
      const query = {
        sort: JSON.stringify([field, order]),
        range: JSON.stringify([(page - 1) * perPage, page * perPage - 1]),
        filter: JSON.stringify(params.filter)
      }
      return { url: `${API_URL}/${resource}?${stringify(query)}` }
    }
    case GET_ONE:
      return { url: `${API_URL}/${resource}/${params.id}` }
    case GET_MANY: {
      const query = {
        filter: JSON.stringify({ id: params.ids })
      }
      return { url: `${API_URL}/${resource}?${stringify(query)}` }
    }
    case GET_MANY_REFERENCE: {
      const { page, perPage } = params.pagination
      const { field, order } = params.sort
      const query = {
        sort: JSON.stringify([field, order]),
        range: JSON.stringify([(page - 1) * perPage, page * perPage - 1]),
        filter: JSON.stringify({ ...params.filter, [params.target]: params.id })
      }
      return { url: `${API_URL}/${resource}?${stringify(query)}` }
    }
    case UPDATE:
      return {
        url: `${API_URL}/${resource}/${params.id}`,
        options: { method: 'PUT', body: JSON.stringify(params.data) }
      }
    case CREATE:
      return {
        url: `${API_URL}/${resource}`,
        options: { method: 'POST', body: JSON.stringify(params.data) }
      }
    case DELETE:
      return {
        url: `${API_URL}/${resource}/${params.id}`,
        options: { method: 'DELETE' }
      }
    case DELETE_MANY:
      const query = {
        filter: JSON.stringify({ id: params.ids })
      }
      return {
        url: `${API_URL}/${resource}?${stringify(query)}`,
        options: { method: 'DELETE' }
      }
    default:
      throw new Error(`Unsupported fetch action type ${type}`)
  }
}

const convertHTTPResponseToDataProvider = (
  response,
  type,
  resource,
  params
) => {
  const { json, headers } = response

  switch (type) {
    case GET_LIST:
      return {
        data: json,
        total: parseInt(
          headers
            .get('content-range')
            .split('/')
            .pop(),
          10
        )
      }
    case CREATE:
      return { data: { ...params.data, id: json.id } }
    default:
      return { data: json }
  }
}

export default (type, resource, params) => {
  const { fetchJson } = fetchUtils

  const accessToken = localStorage.getItem('accessToken')
  const tokenType = localStorage.getItem('tokenType')

  const headersOpt = {
    headers: new Headers({
      Accept: 'application/json',
      Authorization: `${tokenType} ${accessToken}`
    })
  }
  const { url, options } = convertDataProviderRequestToHTTP(
    type,
    resource,
    params
  )
  const opts = Object.assign({}, options, headersOpt)

  return fetchJson(url, opts).then(response =>
    convertHTTPResponseToDataProvider(response, type, resource, params)
  )
}
