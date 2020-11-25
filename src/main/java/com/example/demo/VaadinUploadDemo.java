package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

@Route("")
public class VaadinUploadDemo extends HorizontalLayout implements HasDynamicTitle {

	public VaadinUploadDemo() {
		final TextArea log = new TextArea("LOG");
		log.setReadOnly(true);
		log.setSizeFull();

		final Upload upl = new Upload(new MultiFileMemoryBuffer());
		upl.addSucceededListener(succeededEvent -> {
			appendLog(log, "SucceededEvent occurred for file '" + succeededEvent.getFileName() + "'.");
			// Simulate long processing of uploaded data
			try {
				Thread.sleep(RandomUtils.nextLong(100, 400));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			appendLog(log, "Long operation finished for file '" + succeededEvent.getFileName() + "'.");
		});
		upl.addAllFinishedListener(allFinishedEvent -> appendLog(log, "AllFinishedEvent occurred!"));

		final Button genFilesBtn = new Button("Generate Files");
		genFilesBtn.addClickListener(e -> generateFiles(log));
		genFilesBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		final Button clearLogBtn = new Button("Clear LOG");
		clearLogBtn.addClickListener(e -> log.setValue(log.getEmptyValue()));
		clearLogBtn.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

		final VerticalLayout buttons = new VerticalLayout(genFilesBtn, clearLogBtn);
		buttons.setWidth("15em");

		add(buttons, upl, log);
	}

	private void appendLog(TextArea log, String msg) {
		log.setValue(log.getValue() + LocalDateTime.now() + " " + msg + '\n');
	}

	private void generateFiles(TextArea log) {
		File tempDirectory = createTempDirectory();
		// Create 20 temp files with 2.77 MB random data
		IntStream.range(0, 20).boxed().sorted().forEach(i -> {
			File tempFile = createTempFile(tempDirectory, i);
			appendLog(log, "Created temp file '" + tempFile.toString() + "'.");
			try (OutputStream os = new BufferedOutputStream(new FileOutputStream(tempFile))) {
				StreamUtils.copy(RandomUtils.nextBytes(2915200), os);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		appendLog(log, StringUtils.repeat('-', 100));
	}

	private File createTempDirectory() {
		try {
			File tempDirectory = Files.createTempDirectory("VaadinUploadDemo").toFile();
			tempDirectory.deleteOnExit();
			return tempDirectory;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private File createTempFile(File tempDir, Integer i) {
		File tempFile = new File(tempDir, "upload_test." + StringUtils.leftPad(i.toString(), 3, '0'));
		tempFile.deleteOnExit();
		return tempFile;
	}

	@Override
	public String getPageTitle() {
		return "Vaadin Upload premature AllFinishedEvent demo";
	}

}