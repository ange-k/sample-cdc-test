package contracts.pizz
// https://cloud.spring.io/spring-cloud-contract/2.0.x/multi/multi__contract_dsl.html
org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        urlPath '/reservation/404'
    }
    response {
        status 404
        headers {
            header('Content-Type': 'application/json')
        }
    }
}
