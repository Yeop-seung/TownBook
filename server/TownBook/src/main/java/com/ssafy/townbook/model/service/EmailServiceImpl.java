package hide information.townbook.model.service;

import hide information.townbook.model.repository.AccountRepository;
import java.util.Random;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    
    private JavaMailSender    emailSender;
    private PasswordEncoder   passwordEncoder;
    private AccountRepository accountRepository;
    
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, PasswordEncoder passwordEncoder,
                            AccountRepository accountRepository) {
        this.emailSender       = emailSender;
        this.passwordEncoder   = passwordEncoder;
        this.accountRepository = accountRepository;
    }
    
    public static final String ePw = createKey();
    
    /**
     * 인증번호 메일 메시지
     *
     * @param to
     * @return
     * @throws Exception
     */
    @Override
    public MimeMessage createMessage(String to) throws Exception {
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage message = emailSender.createMimeMessage();
        
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("동네북 회원가입 이메일 인증");//제목
        
        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요 동네북 입니다. </h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<p>감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("townbook1@gmail.com", "동네북"));//보내는 사람
        
        return message;
    }
    
    /**
     * 랜덤한 암호를 만들어서 메일로 보내기
     *
     * @return
     */
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random       rnd = new Random();
        
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
            
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        
        return key.toString();
    }
    
    /**
     * 임시비밀번호 메일 메시지
     *
     * @param to
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public MimeMessage createPasswordMessage(String to, String password) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();
        
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("임시 비밀번호 입니다.");//제목
        
        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요 동네북입니다. </h1>";
        msgg += "<br>";
        msgg += "<p>아래 바뀐 암호를 입력하여 로그인 하십시오<p>";
        msgg += "<br>";
        msgg += "<p>감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>임시 비밀번호 입니다..</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += password + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("townbook1@gmail.com", "동네북"));//보내는 사람
        
        return message;
    }
    
    
    /**
     * 임시 비밀번호 만들기
     *
     * @return
     */
    @Override
    public String getTmpPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        
        String pwd = "";
        
        /* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }
        return pwd;
    }
    
    /**
     * 인증 번호 메일 보내기
     *
     * @param to
     * @return
     * @throws Exception
     */
    @Override
    public String sendSimpleMessage(String to) throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        System.out.println(message);
        try {//예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
    
    /**
     * 새로운 암호 메일 보내기
     *
     * @param to
     * @param password
     * @throws Exception
     */
    @Override
    public void sendPasswordMessage(String to, String password) throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createPasswordMessage(to, password);
        try {//예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}