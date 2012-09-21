package nl.tweeenveertig.openstack.command.object;

import nl.tweeenveertig.openstack.command.core.BaseCommandTest;
import nl.tweeenveertig.openstack.command.core.CommandExceptionError;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class CopyObjectCommandTest extends BaseCommandTest {

    @Before
    public void setup() throws IOException {
        super.setup();
    }

    @Test
    public void deleteContainerSuccess() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(201);
        new CopyObjectCommand(this.account, httpClient, defaultAccess, account.getContainer("containerName"), getObject("objectName"),
                account.getContainer("containerName"), getObject("objectName")).call();
    }

    @Test
    public void deleteContainerDoesNotExist() throws IOException {
        checkForError(404, new CopyObjectCommand(this.account, httpClient, defaultAccess, account.getContainer("containerName"), getObject("objectName"),
                account.getContainer("containerName"), getObject("objectName")), CommandExceptionError.CONTAINER_OR_OBJECT_DOES_NOT_EXIST);
    }

    @Test
    public void unknownError() throws IOException {
        checkForError(500, new CopyObjectCommand(this.account, httpClient, defaultAccess, account.getContainer("containerName"), getObject("objectName"),
                account.getContainer("containerName"), getObject("objectName")), CommandExceptionError.UNKNOWN);
    }
}