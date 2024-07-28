package com.espritech.workerExpress221.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SendOtpByWhatsap {
    //@GetMapping("/send-whatsapp")
//    public String sendWhatsApp() {
////
////        String fromNumber = "+13137658755";
////        String toNumber = "+221766175446";
////        String message = "Hello from your friendly neighborhood Java application!";
////
////        Helpers.WhatsAppUtil.sendWhatsAppMessage(fromNumber, toNumber, message);
////
////        return "WhatsApp message sent!";
//
//
//
//        //public class SendTemplateMessageLib {
//              final String BASE_URL = "nz4rrj.api.infobip.com";
//              final String API_KEY = "5a1c8306b73da7bfb034908966350066-63d059e1-2ff7-4c79-a5df-4cbc7c04d3ca";
//
//             final String SENDER = "447860099299";
//             final String RECIPIENT = "221766175446";
//
//            //public static void main(String[] args) {
//                // Create the API client and the Whatsapp API instances.
//                var apiClient = ApiClient.forApiKey(ApiKey.from(API_KEY))
//                        .withBaseUrl(BaseUrl.from(BASE_URL))
//                        .build();
//                var whatsAppApi = new WhatsAppApi(apiClient);
//
//
//                // Create the content of the message.
//                var content = new WhatsAppTemplateContent()
//                        .language("en")
//                        .templateName("registration_success")
//                        .templateData(new WhatsAppTemplateDataContent()
//                                .header(new WhatsAppTemplateDocumentHeaderContent()
//                                        .mediaUrl("https://api.infobip.com/ott/1/media/infobipLogo")
//                                        .filename("infobipLogo")
//                                )
//                                .body(new WhatsAppTemplateBodyContent()
//                                        .addPlaceholdersItem("sender")
//                                        .addPlaceholdersItem("WhatsApp message")
//                                        .addPlaceholdersItem("delivered")
//                                        .addPlaceholdersItem("exploring Infobip API")
//                                )
//                                .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
//                                        .parameter("Yes"))
//                                .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
//                                        .parameter("No"))
//                                .addButtonsItem(new WhatsAppTemplateQuickReplyButtonContent()
//                                        .parameter("Later"))
//                        );
//
//                // Create a message to send and add to bulk.
//                var message = new WhatsAppMessage()
//                        .from(SENDER)
//                        .to(RECIPIENT)
//                        .content(content);
//                var bulkMessage = new WhatsAppBulkMessage()
//                        .addMessagesItem(message);
//
//                try {
//                    // Send message.
//                    var whatsAppBulkMessageInfo = whatsAppApi.sendWhatsAppTemplateMessage(bulkMessage).execute();
//                    System.out.println("Response body: " + whatsAppBulkMessageInfo);
//                } catch (ApiException e) {
//                    System.out.println("HTTP status code: " + e.responseStatusCode());
//                    System.out.println("Response body: " + e.rawResponseBody());
//                }
//            //}
//        //}
//
//    }
}
