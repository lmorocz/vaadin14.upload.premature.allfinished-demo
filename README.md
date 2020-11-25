# Vaadin 14+ Upload premature AllFinishedEvent demo
This is a demonstration project of how Vaadin's Upload component is sometimes sending AllFinishedEvent premature 
(before all SucceededEventListeners are finished).

## Using
* Vaadin v14.4.3 and also v18.0.0.beta3
* Spring-Boot v2.3.6-RELEASE
* Tomcat 9.0.39 (from Boot's BOM)
* Chrome v87.0.4280.66 (64-bit)
* Firefox v83.0 (64-bit)

## Steps to reproduce
1. Build and run with maven, or your favourite IDE as usual. Load up http://localhost:8080/ in a browser.
2. Hit the "Generate Files"  button to generate some test data with random byte content (20x2.77 MB files in a temp 
directory)
3. Upload the generated files (all 20 of them at once) and watch the LOG TextArea
4. Check the LOG, if the "AllFinishedEvent occurred!" message is not at the bottom then you got the AllFinishedEvent 
premature. If not, then hit "Clear LOG" button, and repeat form 3. 

Eventually (after 2-3 tries) you will see the AllFinishedEvent log between SucceededEvent logs, like this:
```
2020-11-24T15:15:55.615491 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.000'.
2020-11-24T15:15:55.633466300 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.001'.
2020-11-24T15:15:55.645417100 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.002'.
2020-11-24T15:15:55.664358800 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.003'.
2020-11-24T15:15:55.680318500 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.004'.
2020-11-24T15:15:55.693285300 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.005'.
2020-11-24T15:15:55.707244700 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.006'.
2020-11-24T15:15:55.721208800 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.007'.
2020-11-24T15:15:55.733177100 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.008'.
2020-11-24T15:15:55.745145200 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.009'.
2020-11-24T15:15:55.756116 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.010'.
2020-11-24T15:15:55.767087400 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.011'.
2020-11-24T15:15:55.777061 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.012'.
2020-11-24T15:15:55.788031800 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.013'.
2020-11-24T15:15:55.798005500 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.014'.
2020-11-24T15:15:55.808976300 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.015'.
2020-11-24T15:15:55.818950700 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.016'.
2020-11-24T15:15:55.828924 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.017'.
2020-11-24T15:15:55.839895400 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.018'.
2020-11-24T15:15:55.850866 Created temp file 'c:\temp\VaadinUploadDemo12263662277095148568\upload_test.019'.
2020-11-24T15:15:55.861837500 ----------------------------------------------------------------------------------------------------
2020-11-24T15:16:01.302919600 SucceededEvent occurred for file 'upload_test.000'.
2020-11-24T15:16:01.444726700 Long operation finished for file 'upload_test.000'.
2020-11-24T15:16:01.445762600 SucceededEvent occurred for file 'upload_test.001'.
2020-11-24T15:16:01.754658700 Long operation finished for file 'upload_test.001'.
2020-11-24T15:16:01.754658700 SucceededEvent occurred for file 'upload_test.002'.
2020-11-24T15:16:02.018835200 Long operation finished for file 'upload_test.002'.
2020-11-24T15:16:02.018835200 SucceededEvent occurred for file 'upload_test.003'.
2020-11-24T15:16:02.165519300 Long operation finished for file 'upload_test.003'.
2020-11-24T15:16:02.192153600 SucceededEvent occurred for file 'upload_test.005'.
2020-11-24T15:16:02.512011700 Long operation finished for file 'upload_test.005'.
2020-11-24T15:16:02.512011700 SucceededEvent occurred for file 'upload_test.006'.
2020-11-24T15:16:02.635704400 Long operation finished for file 'upload_test.006'.
2020-11-24T15:16:02.635704400 SucceededEvent occurred for file 'upload_test.007'.
2020-11-24T15:16:02.747009800 Long operation finished for file 'upload_test.007'.
2020-11-24T15:16:02.747009800 SucceededEvent occurred for file 'upload_test.004'.
2020-11-24T15:16:02.855218400 Long operation finished for file 'upload_test.004'.
2020-11-24T15:16:02.855218400 SucceededEvent occurred for file 'upload_test.008'.
2020-11-24T15:16:02.981425900 Long operation finished for file 'upload_test.008'.
2020-11-24T15:16:02.996357700 SucceededEvent occurred for file 'upload_test.011'.
2020-11-24T15:16:03.278829500 Long operation finished for file 'upload_test.011'.
2020-11-24T15:16:03.278829500 SucceededEvent occurred for file 'upload_test.012'.
2020-11-24T15:16:03.556922400 Long operation finished for file 'upload_test.012'.
2020-11-24T15:16:03.556922400 SucceededEvent occurred for file 'upload_test.009'.
2020-11-24T15:16:03.849685600 Long operation finished for file 'upload_test.009'.
2020-11-24T15:16:03.849685600 SucceededEvent occurred for file 'upload_test.013'.
2020-11-24T15:16:04.133124300 Long operation finished for file 'upload_test.013'.
2020-11-24T15:16:04.133124300 SucceededEvent occurred for file 'upload_test.010'.
2020-11-24T15:16:04.251426900 Long operation finished for file 'upload_test.010'.
2020-11-24T15:16:04.251426900 SucceededEvent occurred for file 'upload_test.014'.
2020-11-24T15:16:04.367336300 Long operation finished for file 'upload_test.014'.
2020-11-24T15:16:04.376312900 SucceededEvent occurred for file 'upload_test.019'.
2020-11-24T15:16:04.694763700 Long operation finished for file 'upload_test.019'.
2020-11-24T15:16:04.714716900 AllFinishedEvent occurred!
2020-11-24T15:16:04.716694 SucceededEvent occurred for file 'upload_test.017'.
2020-11-24T15:16:04.981976700 Long operation finished for file 'upload_test.017'.
2020-11-24T15:16:05.000931700 SucceededEvent occurred for file 'upload_test.015'.
2020-11-24T15:16:05.389171400 Long operation finished for file 'upload_test.015'.
2020-11-24T15:16:05.389171400 SucceededEvent occurred for file 'upload_test.018'.
2020-11-24T15:16:05.706829 Long operation finished for file 'upload_test.018'.
2020-11-24T15:16:05.706829 SucceededEvent occurred for file 'upload_test.016'.
2020-11-24T15:16:05.949229700 Long operation finished for file 'upload_test.016'.
```

## Current behaviour
Sometimes AllFinishedEventListener runs before all SucceededEventListener (with lengthy processing logic) are finished.

## Expected behaviour
AllFinishedEventListener only runs after all SucceededEventListener (or other failure/abort/rejected EventListeners)
are finished.

## Notes
In the `com.vaadin.flow.component.upload.Upload` constructor the `DomEventListener allFinishedListener` checks whether 
all `element.files` `uploading` boolean is false. If so it calls `fireAllFinish`. 

I debugged the `allFinishedListener` code and noticed that sometimes `element.files` `uploading` boolean is all false, 
but some file statuses are "Processing File...", not empty Strings. At this time the SucceededEventListeners for these 
files (may) have not finished even the upload is complete. I also have seen cases when all the SucceededEventListeners
have finished and 'fireAllFinish' triggered, but some of the `element.files` statuses where "Processing File...". So 
it's a bit hectic ATM. :( However, if I keep debugging the `allFinishedListener` calls, eventually there will be a case, when all `element.files` 
statuses are empty and the `uploading` booleans are all false, it is usually the last call to `allFinishedListener`.

I simulated some long-running operations in the addSucceededListener() with random duration `Thread#sleep calls` to 
demonstrate this. With no lengthy SucceededListener codes the AllFinishedEvent always appeared at the right time.

Maybe it could also check the `status` flag of all `element.files` in `allFinishedListener`: if "not uploading and 
the status is empty" then the upload, and the processing of the uploaded data is all complete.