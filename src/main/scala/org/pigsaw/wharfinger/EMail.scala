package org.pigsaw.wharfinger

import java.util.Properties
import javax.mail.{Transport, Session}
import javax.mail.internet.{MimeMultipart, MimeBodyPart, InternetAddress, MimeMessage}
import javax.mail.util.ByteArrayDataSource
import javax.activation.DataHandler

/**
 * Created by IntelliJ IDEA.
 * User: Nik
 * Date: 12-Sep-2010
 * Time: 19:55:59
 * To change this template use File | Settings | File Templates.
 */

class EMail(val to: String,
        toName: String,
        from: String,
        fromName: String,
        subject: String,
        bodyText: String) {

  val props = new Properties
  val session = Session.getDefaultInstance(props, null)
  val message = new MimeMessage(session)
  val multipart = new MimeMultipart("mixed")

  message.setFrom(new InternetAddress(from, fromName))
  message.addRecipient(javax.mail.Message.RecipientType.TO,
    new InternetAddress(to, toName))
  message.setSubject(subject)

  val text_part = new MimeBodyPart
  text_part.setContent(bodyText, "text/plain")
  multipart.addBodyPart(text_part)

  def send() {
    message.setContent(multipart)
    Transport.send(message)
  }

  def attachHTML(filename: String, html: String) {
    /*val attachment = new MimeBodyPart
    attachment.setFileName(filename)
    attachment.setContent(html, "text/html")
    multipart.addBodyPart(attachment)*/
    val attachment = new MimeBodyPart
    attachment.setHeader("Content-Type", "text/html; charset=UTF-8");
    val byteArrayDataSource = new ByteArrayDataSource(html, "text/html; charset=UTF-8");
    attachment.setDataHandler(new DataHandler(byteArrayDataSource));
    attachment.setFileName(filename);
    multipart.addBodyPart(attachment);

    message.setContent(multipart,"text/html; charset=UTF-8");

  }
}