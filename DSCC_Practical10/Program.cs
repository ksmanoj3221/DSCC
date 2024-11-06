using System;
using System.Threading.Tasks;
using Amazon.S3;
using Amazon.S3.Model;
using Amazon;

public class S3Example
{
    private readonly IAmazonS3 _s3Client;

    public S3Example()
    {
        // Specify the region you are using
        var region = RegionEndpoint.USEast1; // Change this to your desired region
        _s3Client = new AmazonS3Client(region); // Ensure your credentials are configured properly
    }

    public async Task ListBucketsAsync()
    {
        var response = await _s3Client.ListBucketsAsync();
        foreach (var bucket in response.Buckets)
        {
            Console.WriteLine($"Bucket name: {bucket.BucketName}, Created on: {bucket.CreationDate}");
        }
    }

    public static async Task Main(string[] args)
    {
        var example = new S3Example();
        await example.ListBucketsAsync();
    }
}
