package contracts.pizz
// https://cloud.spring.io/spring-cloud-contract/2.0.x/multi/multi__contract_dsl.html
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        urlPath '/reservation'
        headers {
            header('Content-Type': 'application/json')
        }
        body("""
        {
          "pizza": "ナポリ",
          "topping": [
            "サラミ",
            "ウインナー",
            "フランクフルト",
            "えび"
          ]
        }
        """)
    }
    response {
        status 201
    }
}
