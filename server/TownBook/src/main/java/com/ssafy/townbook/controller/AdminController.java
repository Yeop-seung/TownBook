package hide information.townbook.controller;

import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import hide information.townbook.model.service.AdminService;
import hide information.townbook.model.service.MyPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    private AdminService adminService;
    
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    
    @Autowired
    private MyPageServiceImpl myPageService;
    
    /**
     * 전체 회원 조회
     *
     * @return List<AccountDto>
     */
    @GetMapping("")
    public ResponseEntity<FindListResponseDto> findAllAccounts() {
        return new ResponseEntity<FindListResponseDto>(adminService.findAllAccounts(), HttpStatus.OK);
    }
    
    /**
     * 회원번호로 단일 회원을 조회
     *
     * @param accountNo
     * @return AccountDto
     */
    @GetMapping("/{accountNo}")
    public ResponseEntity<FindOneResponseDto> findAccountByAccountNo(@PathVariable Long accountNo) {
        return new ResponseEntity<FindOneResponseDto>(adminService.findAccountByAccountNo(accountNo), HttpStatus.OK);
    }


    /**
     * 특정 회원에게 포인트 부여
     *
     * @param accountNo
     * @return Integer
     */
    @Transactional(readOnly = false)
    @GetMapping("/giveOne/{accountNo}/{point}")
    public ResponseEntity<FindOneResponseDto> givePointOneUser(@PathVariable Long accountNo, @PathVariable Integer point){
        return new ResponseEntity<FindOneResponseDto>(adminService.givePointOneUser(accountNo,point),HttpStatus.OK);
    }

    /**
     * Type이 1인 유저 : 취약계층 전체 회원에게 포인트 부여
     *
     * @param point
     * @return Boolean
     */
    @Transactional(readOnly = false)
    @GetMapping("/giveType/{point}")
    public ResponseEntity<FindOneResponseDto> givePointTypeUser(@PathVariable Integer point){
        return new ResponseEntity<FindOneResponseDto>(adminService.givePointTypeUser(point),HttpStatus.OK);
    }
}
