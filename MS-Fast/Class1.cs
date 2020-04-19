using System;
using System.IO;
using System.Xml;
using Microsoft.XmlDiffPatch;


namespace ConsoleApplication2
{
	/// <summary>
	/// Summary description for Class1.
	/// </summary>
	class Class1
	{
		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main(string[] args)
		{
			int from = int.Parse(args[0]);
			int to = int.Parse(args[1]);
			int step = int.Parse(args[2]);
			int numberOfTests = int.Parse(args[3]);
			FileStream fsOut = new FileStream("testcases\\totalLog.txt", FileMode.Create, FileAccess.Write, FileShare.None);
			StreamWriter sw = new StreamWriter(fsOut);
			sw.WriteLine("Start: " + System.DateTime.Now);

			for(int testcase=from; testcase<=to; testcase+=step) 
			{
				Console.Write("testcase # " + testcase);
				String source = "testcases\\" + testcase + "\\File1.xml";
				String dest = "testcases\\" + testcase + "\\File2.xml";
				String resultFile = "testcases\\" + testcase + "\\result.xml";
				
				FileStream fsOut2 = new FileStream("testcases\\" + testcase+ "\\log.txt", FileMode.Create, FileAccess.Write, FileShare.None);
				StreamWriter sw2 = new StreamWriter(fsOut2);

				long[] results = new long[numberOfTests];
				for(int i=0; i < numberOfTests; i++) 
				{
					XmlReader sourceReader = new XmlTextReader(source);
					XmlReader destReader = new XmlTextReader(dest);
					XmlWriter diffgramWriter = new XmlTextWriter(resultFile, System.Text.Encoding.Default);
					System.GC.Collect();
					XmlDiff myDiff = new XmlDiff();
					myDiff.Algorithm = XmlDiffAlgorithm.Fast;

					DateTime start = System.DateTime.Now;
					bool bSame = myDiff.Compare(sourceReader, destReader,diffgramWriter);
					DateTime end = System.DateTime.Now;
					long spentTime = (end.Ticks - start.Ticks) / 10000;
					results[i] = spentTime;
					sourceReader.Close();
					destReader.Close();
					diffgramWriter.Close();
					sw2.WriteLine(spentTime);
					myDiff = null;
					System.GC.Collect();
				}
				long sum = 0;
				for(int i =0; i< numberOfTests; i++) 
				{
					sum += results[i];
				}
				float avg = (float) sum / (float) numberOfTests;
				Console.WriteLine("avg: "+avg);
				sw2.WriteLine("avg: "+avg);
				sw.WriteLine(testcase +" , "+avg);
				sw2.Close();
			}
			sw.WriteLine("Finish: " + System.DateTime.Now);
			sw.Close();
			//
			// TODO: Add code to start application here
			//
		}
	}
}
