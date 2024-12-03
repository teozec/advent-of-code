using System.Text.RegularExpressions;

namespace AdventOfCode2023
{
    internal class Day6
    {
        private static int WinningCombinations(long time, long distance)
        {
            double sqrtDelta = Math.Sqrt(time * time - 4d * distance);
            double t1 = (time + sqrtDelta) / 2d;
            double t2 = (time - sqrtDelta) / 2d;
            return (int)(Math.Floor(t1) - Math.Ceiling(t2)) + 1;
        }

        private static IEnumerable<(long time, long distance)> GetInput1(IEnumerable<string> input)
        {
            var timesDescription = input.First();
            var distancesDescription = input.Last();

            Regex re = new(@".*:( +(?<value>\d+))+");
            var times = re.Match(timesDescription).Groups["value"].Captures.Select(time => long.Parse(time.Value));
            var distances = re.Match(distancesDescription).Groups["value"].Captures.Select(distance => long.Parse(distance.Value));

            return times.Zip(distances);
        }
        private static (long time, long distance) GetInput2(IEnumerable<string> input)
        {
            var timesDescription = input.First();
            var distancesDescription = input.Last();

            Regex re = new(@".*:( +(?<value>\d+))+");
            var time = long.Parse(re.Match(timesDescription).Groups["value"].Captures.Aggregate(string.Empty, (x, y) => x + y.Value));
            var distance = long.Parse(re.Match(distancesDescription).Groups["value"].Captures.Aggregate(string.Empty, (x, y) => x + y.Value));

            return (time, distance);
        }


        public static int Exercise1()
            => GetInput1(File.ReadLines("Inputs/Day6.txt"))
                .Select(x => WinningCombinations(x.time, x.distance))
                .Aggregate((x, y) => x * y);

        public static int Exercise2()
        {
            var (time, distance) = GetInput2(File.ReadLines("Inputs/Day6.txt"));
            return WinningCombinations(time, distance);
        }

    }
}
