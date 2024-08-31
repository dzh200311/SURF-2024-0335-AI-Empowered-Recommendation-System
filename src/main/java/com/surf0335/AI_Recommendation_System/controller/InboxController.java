package com.surf0335.AI_Recommendation_System.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.surf0335.AI_Recommendation_System.model.Email;
import com.surf0335.AI_Recommendation_System.model.MailModel;
import com.surf0335.AI_Recommendation_System.repository.CourseRepository;
import com.surf0335.AI_Recommendation_System.repository.EmailRepository;
import com.surf0335.AI_Recommendation_System.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InboxController {
    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/inbox")
    public String getinboxPage(Model m){
        String username = "XD2655512936@outlook.com";
        String password = "xinda20030308";



        List<MailModel> mailModels = EmailUtil.readMail(username,password,"outlook.office365.com","993","imaps");
        for (MailModel mailModel : mailModels) {
            String md5 = DigestUtil.md5Hex(mailModel.getContext());
            List<Email> duplicateMail = emailRepository.findDuplicateMail(md5);
            if (CollUtil.isEmpty(duplicateMail)){
                Email entity = new Email();
                entity.setMd5(md5);
                entity.setSender(mailModel.getSender());
                entity.setSubject(mailModel.getSubject());
                entity.setContent(mailModel.getContext());
                entity.setDate(mailModel.getCreateDateTime());
                emailRepository.save(entity);
            }
        }
        m.addAttribute("mailMsgList",mailModels);
        return "Inbox";
    }

/*    @Autowired
    private EmailService emailService; // Assume you have an EmailService to fetch emails
    @Autowired
    private TextMiningService textMiningService; // Assume you have a TextMiningService to process emails

    @GetMapping("/inbox")
    public String getInbox(Model model) {
        List<Email> emails = emailService.getAllEmails();
        model.addAttribute("emails", emails);
        return "inbox";
    }

    @PostMapping("/extract")
    public String extractInformation(@RequestParam("emailId") Long emailId, Model model) {
        Email selectedEmail = emailService.getEmailById(emailId);
        ExtractedInfo extractedInfo = textMiningService.extractInformation(selectedEmail.getContent());

        // Assuming you want to create a calendar notification
        CalendarNotification notification = new CalendarNotification();
        notification.setTitle("Meeting with " + extractedInfo.getStudentName());
        notification.setDate(extractedInfo.getDate());
        notification.setDescription("Meeting with " + extractedInfo.getStudentName() + " from " + extractedInfo.getSchoolName());

        // Add the notification to the model
        model.addAttribute("notification", notification);

        // Reload the inbox view with the selected email and extracted info
        model.addAttribute("selectedEmail", selectedEmail);
        List<Email> emails = emailService.getAllEmails();
        model.addAttribute("emails", emails);

        return "inbox";
    }*/
}
