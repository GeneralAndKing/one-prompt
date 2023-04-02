export const formatTime = (date: Date) => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return (
    [year, month, day].map(formatNumber).join('/') +
    ' ' +
    [hour, minute, second].map(formatNumber).join(':')
  )
}

const formatNumber = (n: number) => {
  const s = n.toString()
  return s[1] ? s : '0' + s
}

export const parseQueryParams = (queryParams: string): Record<string, string> => {
  const queryParamsObject: Record<string, string> = {}
  const regex = /([^&=]+)=([^&]*)/g
  let match: RegExpExecArray | null
  while ((match = regex.exec(queryParams))) {
    queryParamsObject[decodeURIComponent(match[1])] = decodeURIComponent(match[2])
  }
  return queryParamsObject
}