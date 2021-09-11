# LottoData API's
Lotto Data

# **Lotto JSON:

```json
{
  "numberOfTicket": "Integer",
  "date": "Date in format 'd.M.yy' or 'dd.MM.yyyy'",
  "combination": {
    "first": "Integer",
    "second": "Integer",
    "third": "Integer",
    "fourth": "Integer",
    "fifth": "Integer",
    "sixth": "Integer",
    "strong": "Integer"
  }
}
```

### -> 1. POST {hostname}/add/ticket
- Headers : -
- Query Parameters:
  - With param - `?number={Integer}&day={Integer}&month={Integer}&year={Integer}`
- Body: Json:
  ```json
  {
    "first": "Integer",
    "second": "Integer",
    "third": "Integer",
    "fourth": "Integer",
    "fifth": "Integer",
    "sixth": "Integer",
    "strong": "Integer"
  }
- Responses:
    - `Status: 200 OK` - `Body` - List of JSON's: `*Lotto JSON`

    - `Status: 204 No Content`

### -> 2. DELETE {hostname}/delete/ticket
- Headers : -
- Query Parameters:
  - With param - `?number={Integer}` will remove a one ticket by number
- Body: -
- Responses:
    - `Status: 200 OK` - `Body` - JSON: `*Lotto JSON`

    - `Status: 204 No Content`

### -> 3. GET {hostname}/get/ticket
- Headers : -
- Query Parameters:
  - With param - `?number={Integer}` will return a one ticket by number
- Body: -
- Responses:
  - `Status: 200 OK` - `Body` - JSON: `*Lotto JSON`

  - `Status: 204 No Content`

### -> 4. GET {hostname}/get/tickets
- Headers : -
- Query Parameters:
  - With param - `?fromDate={Date in format 'd.M.yy' or 'dd.MM.yyyy'}&toDate={Date in format 'd.M.yy' or 'dd.MM.yyyy'}` will return a list of tickets by date range
- Body: -
- Responses:
  - `Status: 200 OK` - `Body` - JSON: `*Lotto JSON`

  - `Status: 204 No Content`
