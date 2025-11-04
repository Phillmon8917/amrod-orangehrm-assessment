package automation.email;

import automation.properties.ConfigPropInstance;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class Email
{

    private static final Dotenv dotenv = Dotenv.load();

    public static void sendEmail()
    {
        File zippedReport = zipResultsFolder("Results");
        if (zippedReport == null)
        {
            log.error("Failed to zip Results folder.");
            return;
        }

        String from = dotenv.get("EMAILUSERNAME");
        String to = ConfigPropInstance.getConfig().toEmail().trim();
        String cc = ConfigPropInstance.getConfig().ccEmail().trim();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, dotenv.get("EMAILPASSWORD"));
            }
        });

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            if (!cc.isEmpty())
            {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }

            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            message.setSubject("Test Results - " + dateTime);

            MimeBodyPart bodyPart = new MimeBodyPart();
            String htmlContent = "<div style='background-color:#f2f2f2; padding:20px; font-family:Arial, sans-serif;'>"
                    + "<h3 style='color:#333;'>Good day,</h3>"
                    + "<p>Kindly find the attached test results for your review.</p>"
                    + "<p style='margin-top:30px;'>Kind regards,<br>QA Team</p>"
                    + "</div>";
            bodyPart.setContent(htmlContent, "text/html; charset=utf-8");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(zippedReport);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
            Transport.send(message);

            log.info("Email sent successfully to " + to + " with CC to " + cc);
        } catch (Exception ex)
        {
            log.error("An error occurred while sending the email", ex);
        }
    }

    private static File zipResultsFolder(String folderName)
    {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path zipPath = Paths.get(folderName + "_" + timestamp + ".zip");

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipPath)))
        {
            Path sourceDir = Paths.get(folderName);
            Files.walk(sourceDir)
                    .filter(Files::isRegularFile)
                    .forEach(path ->
                    {
                        ZipEntry zipEntry = new ZipEntry(sourceDir.relativize(path).toString());
                        try
                        {
                            zos.putNextEntry(zipEntry);
                            Files.copy(path, zos);
                            zos.closeEntry();
                        } catch (IOException e)
                        {
                            log.error("Error adding file to zip: " + path, e);
                        }
                    });
            return zipPath.toFile();
        } catch (IOException e)
        {
            log.error("Error creating zip file", e);
            return null;
        }
    }
}