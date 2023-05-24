package hide information.townbook.model.service;

import com.google.zxing.WriterException;
import hide information.townbook.model.dto.response.FindListResponseDto;
import hide information.townbook.model.dto.response.FindOneResponseDto;
import java.io.IOException;
import java.util.Optional;

public interface MyPageService {
    
    Optional<Object> getQrCode(String qrSource) throws WriterException, IOException;
    
    FindOneResponseDto findPointByAccountNo(Long accountNo) throws Exception;
    
    FindListResponseDto findBookLogByAccountNo(Long accountNo) throws Exception;
    
    FindListResponseDto findDonateBookLogByAccountNo(Long accountNo) throws Exception;
    
    FindListResponseDto findReceiveBookLogByReceiverNo(Long receiverNo) throws Exception;
    
    FindListResponseDto findWishListByAccountNo(Long accountNo) throws Exception;
}
