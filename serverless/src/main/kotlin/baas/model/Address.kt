package baas.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument
import kotlinx.serialization.Serializable

@Serializable
@DynamoDBDocument
data class Address(
    @DynamoDBAttribute(attributeName = "addressLineOne")
    var addressLineOne: String? = null,
    @DynamoDBAttribute(attributeName = "addressLineTwo")
    var addressLineTwo: String? = null,
    @DynamoDBAttribute(attributeName = "city")
    var city: String? = null,
    @DynamoDBAttribute(attributeName = "state")
    var state: String? = null,
    @DynamoDBAttribute(attributeName = "zipCode")
    var zipCode: String? = null
)