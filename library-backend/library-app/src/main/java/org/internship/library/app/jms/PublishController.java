package org.internship.library.app.jms;

import org.internship.library.api.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jms")
public class PublishController
{

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/publishBook")
    public ResponseEntity<?> publishMessage(@RequestBody BookDTO bookDTO)
    {
        try
        {
            jmsTemplate.convertAndSend("publishBook", bookDTO);

            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
