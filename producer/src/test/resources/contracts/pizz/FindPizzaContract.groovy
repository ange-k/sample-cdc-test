package contracts.pizz
// https://cloud.spring.io/spring-cloud-contract/2.0.x/multi/multi__contract_dsl.html
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        urlPath '/reservation/2' // ここを固定しないと、Producer側単体テストの準備が難しい
    }
    response {
        status 200
        headers {
            header('Content-Type': 'application/json')
        }
        body("""
        {
          "id": 1234567890,
          "pizza": "ナポリ",
          "topping": [
            "サラミ",
            "ウインナー",
            "フランクフルト",
            "えび"
          ]
        }
        """)
        bodyMatchers {
            jsonPath('$.id', byRegex('[0-9]{1,10}'))
            jsonPath('$.pizza', byType())
            jsonPath('$.topping', byType())
            jsonPath('$.topping[0]', byType())
        }
    }
}
