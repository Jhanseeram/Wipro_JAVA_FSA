var HttpStatusCode;
(function (HttpStatusCode) {
    HttpStatusCode[HttpStatusCode["OK"] = 200] = "OK";
    HttpStatusCode[HttpStatusCode["NOT_FOUND"] = 404] = "NOT_FOUND";
    HttpStatusCode[HttpStatusCode["ACCESS_DENIED"] = 403] = "ACCESS_DENIED";
    HttpStatusCode[HttpStatusCode["INTERNAL_ERROR"] = 500] = "INTERNAL_ERROR";
})(HttpStatusCode || (HttpStatusCode = {}));
console.log(HttpStatusCode.OK);
