fun main() {
    LoadBalancer().serverRequest(Request("Hello"))
    LoadBalancer().serverRequest(Request("Hello World"))
}