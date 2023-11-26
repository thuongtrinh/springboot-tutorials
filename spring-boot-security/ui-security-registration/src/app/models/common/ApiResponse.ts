import * as t from 'io-ts'

export interface ApiResponse<A> {
  code: string,
  message: string,
  data: A
}

export function ApiResponse<A extends t.Mixed>(a: A): t.Type<ApiResponse<A>>
export function ApiResponse<A extends t.Mixed>(a: A) {
  return t.type({
    code: t.string,
    message: t.string,
    data: a,
  })
}
