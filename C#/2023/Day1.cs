using System.Text.RegularExpressions;

namespace AdventOfCode2023
{
    internal class Day1
    {

        private static int GetNumberFromLine(string line)
            => int.Parse($"{line.First(char.IsDigit)}{line.Last(char.IsDigit)}");

        public static int Exercise1()
            => File.ReadLines("Inputs/Day1.txt")
                .Select(GetNumberFromLine)
                .Sum();


        private static int GetNumberFromLineWithWords(string line)
        {
            Dictionary<string, string> map = new()
            {
                { "one", "1" },
                { "two", "2" },
                { "three", "3" },
                { "four", "4" },
                { "five", "5" },
                { "six", "6" },
                { "seven", "7" },
                { "eight", "8" },
                { "nine", "9" }
            };

            string subRe = string.Join("|", map.Keys);
            string matchRe = $"\\d|{subRe}";

            string left = Regex.Replace(
                Regex.Match(line, matchRe).Value,
                subRe,
                m => map[m.Value]);

            string right = Regex.Replace(
                Regex.Match(line, matchRe, RegexOptions.RightToLeft).Value,
                subRe,
                m => map[m.Value]);

            return int.Parse($"{left}{right}");
        }

        public static int Exercise2()
            => File.ReadLines("Inputs/Day1.txt")
                .Select(GetNumberFromLineWithWords)
                .Sum();

    }

}
