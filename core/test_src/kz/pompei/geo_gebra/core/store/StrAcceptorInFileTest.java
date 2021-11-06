package kz.pompei.geo_gebra.core.store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import kz.greetgo.util.RND;
import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;

public class StrAcceptorInFileTest {

  private final Path testDir = Paths.get("build/test_dirs/" + getClass().getSimpleName());

  @Test
  public void get__noFile() {

    var acceptor = new StrAcceptorInFile(testDir.resolve(RND.str(10) + ".txt"));

    //
    //
    var str = acceptor.get();
    //
    //

    assertThat(str).isNull();
  }

  @Test
  public void get__fileExists() throws IOException {

    var file = testDir.resolve(RND.str(10) + ".txt");

    file.toFile().getParentFile().mkdirs();

    String expected = RND.str(20);

    Files.writeString(file, expected);

    var acceptor = new StrAcceptorInFile(file);

    //
    //
    String str = acceptor.get();
    //
    //

    assertThat(str).isEqualTo(expected);
  }

  @Test
  public void set__noFile() throws IOException {

    var file = testDir.resolve(RND.str(10))
                      .resolve(RND.str(10))
                      .resolve(RND.str(10) + ".txt");

    var acceptor = new StrAcceptorInFile(file);

    var expected = RND.str(20);

    //
    //
    acceptor.set(expected);
    //
    //

    var str = Files.readString(file);

    assertThat(str).isEqualTo(expected);
  }

  @Test
  public void set__existsFile() throws IOException {

    var file = testDir.resolve(RND.str(10))
                      .resolve(RND.str(10))
                      .resolve(RND.str(10) + ".txt");

    file.toFile().getParentFile().mkdirs();
    Files.writeString(file, RND.str(10));

    var acceptor = new StrAcceptorInFile(file);

    var expected = RND.str(20);

    //
    //
    acceptor.set(expected);
    //
    //

    var str = Files.readString(file);

    assertThat(str).isEqualTo(expected);
  }

}
