package health.check

import jakarta.persistence.*

@Entity
data class HealthCheck(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(name = "status")
    var status: String? = null
)
