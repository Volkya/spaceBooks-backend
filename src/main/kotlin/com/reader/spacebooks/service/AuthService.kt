//package service
//
//import org.springframework.stereotype.Service
//import org.springframework.web.client.HttpClientErrorException.Unauthorized
//
//@Service
//class AuthService {
//
//
//
//    fun ctx200Services(authorization: String, service: String, requestBody: ServiceRequest, isV2: Boolean, isV3: String): ServiceResponse {
//
//        var configRouteResponse: ConfigRouteResponse
//
//        try {
//            var customerId: String? = customerIdHandler.getCustomerId(requestBody)
//            configRouteResponse = routeValidator.validate(
//                service,
//                requestBody.config.channelData.codeType,
//                requestBody.config.connection.userId,
//                authorization,
//                customerId,
//            )
//            configRouteResponse.txId = configRouteResponse.txId ?: requestBody.config.connection.transactionId
//            return callCtx200Services(configuRouteResponse, service, requestBody, isV2, isV3);
//        } catch (e: RouteConfigNotFoundException){
//            configRouteResponse = e.route!!
//            configRouteResponse.txId = requestBody.config.connection.transactionId
//            return callCtx200Services(configRouteResponse, service, requestBody, isV2, isV3);
//        }
//
//    }
//
//
//
//    fun validate(service: String, channel: String?, user: String?, apikeyEncrypt: String, customerId: String?): ConfigRouteResponse {
//        val apikeyEncryptList = Base64Util.decode(apikeyEncrypt.replace(BEARER_PREFIX, "")).split(":")
//        if(apikeyEncryptList.size < 2){
//            throw UnauthorizedException(ERROR_KEY, ERROR_KEY)
//        }
//
//        var clientId = apikeyEncryptList[0]
//        var clientSecret = EncryptUtil.sha256Encrypt(apikeyEncryptList[1])
//
//        var application : ApplicationKeyValueResponse = this.getApplication(
//
//        )
//    }
//
//
//}