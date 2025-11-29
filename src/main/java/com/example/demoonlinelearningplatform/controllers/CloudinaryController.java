package com.example.demoonlinelearningplatform.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller này dùng để lưu trữ video bằng việc gọi đến 1 kho lưu trữ đám mây miễn phí nhưng có giới hạn upload hàng tháng
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/cloudinary")
@RequiredArgsConstructor
public class CloudinaryController {

    private final Cloudinary cloudinary;

    // Free upload video 10GB/account, mỗi lần upload video tối đa 100MB
    // Gói Free Cloudinary có 25GB bandwidth (băng thông) mỗi tháng
    public CloudinaryController() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "deauthjwg",
                "api_key", "934874946381839",
                "api_secret", "KuleJgw7RKyk0DI1_IxdF2r4i9I"
        ));
    }

    /**
     * API này dùng để lấy thông tin bảo mật của Cloudinary
     *
     * @return thông tin của tài khoản đã đăng kí ở Cloud
     */
    @GetMapping("/signature")
    public ResponseEntity<Map<String, String>> getSignature() {
        long timestamp = System.currentTimeMillis() / 1000;

        Map<String, Object> paramsToSign = new HashMap<>();
        paramsToSign.put("timestamp", timestamp);

        String signature = cloudinary.apiSignRequest(paramsToSign, "KuleJgw7RKyk0DI1_IxdF2r4i9I");

        Map<String, String> response = new HashMap<>();
        response.put("signature", signature);
        response.put("timestamp", String.valueOf(timestamp));
        response.put("apiKey", "934874946381839");
        response.put("cloudName", "deauthjwg");

        return ResponseEntity.ok(response);
    }

    /**
     * API này dùng để xóa video đã upload lên Cloud
     *
     * @param publicId: id của video
     * @return message
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteVideo(@RequestParam String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("resource_type", "video"));

            if ("ok".equals(result.get("result"))) {
                return ResponseEntity.ok("Xóa thành công");
            } else {
                return ResponseEntity.status(404).body("Không tìm thấy video");
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Lỗi khi xóa video: " + e.getMessage());
        }
    }

    /**
     * API này dùng kiểm tra xem đã có bao nhiêu dung lượng
     *
     * @return thông tin
     */
    @GetMapping("/usage")
    public ResponseEntity<?> getUsageInfo() {
        try {
            Map usage = cloudinary.api().usage(ObjectUtils.emptyMap());

            // Trích xuất thông tin quan trọng
            Map<String, Object> data = new HashMap<>();
            Map storage = (Map) usage.get("storage");
            data.put("total_storage_used_mb", storage.get("usage").toString());
            data.put("quota_limit_mb", storage.get("limit").toString());

            Map bandwidth = (Map) usage.get("bandwidth");
            data.put("bandwidth_used_mb", bandwidth.get("usage").toString());
            data.put("bandwidth_limit_mb", bandwidth.get("limit").toString());

            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi lấy thông tin quota: " + e.getMessage());
        }
    }
}

