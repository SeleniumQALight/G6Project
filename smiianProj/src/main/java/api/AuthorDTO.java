package api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // анотація що включає Геттери і сеттери + ще щось
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthorDTO {
    String username;
    String avatar;

//    public AuthorDTO(){   // замість цього "@NoArgsConstructor"
//
//    }

//    public AuthorDTO(String username) {   // ПКМ / Generate / Constructor
//        this.username = username;
//    }

//    public String getUsername() {                              //  те саме що і анотація  "@Data"
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//
//    public String getAvatar() {
//        return avatar;
//    }
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }

//    @Override
//    public String toString() {
//        return "AuthorDTO{" +
//                "username='" + username + '\'' +
//                ", avatar='" + avatar + '\'' +
//                '}';
//    }
}
