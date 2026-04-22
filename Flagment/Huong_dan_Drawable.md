# 🎨 Hướng dẫn sử dụng Drawable Background cho Button & EditText

---

## 1. Drawable là gì?

Drawable là file XML nằm trong thư mục `res/drawable/`, dùng để **vẽ hình dạng** (shape) làm nền cho View (Button, EditText, LinearLayout,...) thay vì dùng ảnh bitmap.

### Ưu điểm so với dùng màu trực tiếp

| Dùng màu trực tiếp | Dùng Drawable |
|---|---|
| `android:background="#3AA76D"` | `android:background="@drawable/bg_button_primary"` |
| Nền vuông góc, xấu | Bo góc tròn, đẹp |
| Không có viền | Có thể thêm viền (stroke) |
| Khó tái sử dụng | Dùng lại ở nhiều nơi |

---

## 2. Ba File Drawable Trong Dự Án

### 2.1 `bg_button_primary.xml` — Nút xanh đặc (Sign In, Log In, Send Code,...)

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/primary_green"/>   <!-- Màu nền đặc -->
    <corners android:radius="12dp"/>                <!-- Bo góc 12dp -->
</shape>
```

### 2.2 `bg_button_outline.xml` — Nút viền xanh, nền trong suốt (Create Account)

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@android:color/transparent"/>              <!-- Nền trong suốt -->
    <stroke android:width="1dp" android:color="@color/primary_green"/> <!-- Viền xanh 1dp -->
    <corners android:radius="12dp"/>
</shape>
```

### 2.3 `bg_edit_text.xml` — Ô nhập liệu (Email, Password)

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/edittext_bg_gray"/>              <!-- Nền xám nhạt -->
    <stroke android:width="1dp" android:color="#E0E0E0"/>          <!-- Viền xám -->
    <corners android:radius="12dp"/>
</shape>
```

---

## 3. Cách Sử Dụng Trong Layout XML

### 3.1 Gán cho Button

> ⚠️ **LƯU Ý QUAN TRỌNG:** Khi dùng Material Theme (mặc định trong Android mới), hệ thống tự phủ một lớp `backgroundTint` màu tím lên Button. **Phải thêm `android:backgroundTint="@null"`** để tắt lớp phủ này, nếu không drawable sẽ không hiển thị đúng màu!

Dùng thuộc tính `android:background` + `android:backgroundTint="@null"`:

```xml
<!-- Nút xanh đặc -->
<Button
    android:id="@+id/btnSignIn"
    android:layout_width="match_parent"
    android:layout_height="56dp"
    android:background="@drawable/bg_button_primary"
    android:text="Sign In"
    android:textColor="@color/white"
    android:textSize="16sp"
    android:textStyle="bold" />

<!-- Nút viền xanh -->
<Button
    android:id="@+id/btnCreateAccount"
    android:layout_width="match_parent"
    android:layout_height="56dp"
    android:background="@drawable/bg_button_outline"
    android:text="Create Account"
    android:textColor="@color/primary_green"
    android:textSize="16sp"
    android:textStyle="bold" />
```

### 3.2 Gán cho EditText

```xml
<EditText
    android:id="@+id/etEmail"
    android:layout_width="match_parent"
    android:layout_height="56dp"
    android:background="@drawable/bg_edit_text"
    android:hint="Email address"
    android:textColorHint="@color/text_hint_gray"
    android:paddingStart="16dp"
    android:paddingEnd="16dp"
    android:inputType="textEmailAddress" />
```

---

## 4. Giải Thích Các Thẻ Trong Shape

| Thẻ | Tác dụng | Ví dụ |
|---|---|---|
| `<shape>` | Khai báo hình dạng | `android:shape="rectangle"` (hình chữ nhật) |
| `<solid>` | Màu nền đặc | `android:color="@color/primary_green"` |
| `<stroke>` | Viền bao quanh | `android:width="1dp" android:color="#E0E0E0"` |
| `<corners>` | Bo góc tròn | `android:radius="12dp"` |
| `<padding>` | Khoảng cách bên trong | `android:left="16dp"` |

---

## 5. Ví Dụ Thực Tế — Áp Dụng Vào Welcome Screen

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="32dp"
    android:background="@color/white">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Explore the app"
        android:textSize="28sp"
        android:textStyle="bold"
        android:textColor="@color/text_dark"
        android:layout_marginBottom="48dp" />

    <!-- ✅ Dùng bg_button_primary -->
    <Button
        android:id="@+id/btnSignIn"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:layout_marginBottom="16dp"
        android:background="@drawable/bg_button_primary"
        android:text="Sign In"
        android:textColor="@color/white"
        android:textSize="16sp"
        android:textAllCaps="false" />

    <!-- ✅ Dùng bg_button_outline -->
    <Button
        android:id="@+id/btnCreateAccount"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:background="@drawable/bg_button_outline"
        android:text="Create account"
        android:textColor="@color/primary_green"
        android:textSize="16sp"
        android:textAllCaps="false" />

</LinearLayout>
```

---

## 6. Tóm Tắt

```
res/drawable/
 ├── bg_button_primary.xml   → android:background="@drawable/bg_button_primary"
 ├── bg_button_outline.xml   → android:background="@drawable/bg_button_outline"
 └── bg_edit_text.xml        → android:background="@drawable/bg_edit_text"
```

> **Quy tắc:** Tạo file shape XML 1 lần trong `drawable/`, rồi gán vào bất kỳ View nào bằng `android:background="@drawable/tên_file"`. Không cần lặp lại code shape ở nhiều nơi.
