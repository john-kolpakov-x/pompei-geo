package kz.pompei.geo_gebra.core.store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("ClassCanBeRecord")
public class StrAcceptorInFile implements StrAcceptor {

  private final Path filePath;

  public StrAcceptorInFile(Path filePath) {
    this.filePath = filePath;
  }

  @Override
  public String get() {
    if (!Files.exists(filePath)) {
      return null;
    }

    try {
      return Files.readString(filePath);
    } catch (java.nio.file.NoSuchFileException e) {
      return null;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void set(String str) {
    filePath.toFile().getParentFile().mkdirs();
    try {
      Files.writeString(filePath, str);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
