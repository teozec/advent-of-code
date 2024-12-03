using System.Collections.Immutable;
using System.Text.RegularExpressions;

namespace AdventOfCode2023
{
    internal class Day4
    {
        private class ScratchCard
        {
            private readonly int id;
            private readonly IEnumerable<int> winningNumbers;
            private readonly IEnumerable<int> ownedNumbers;

            private IEnumerable<int> OwnedWinningNumbers { get => winningNumbers.Intersect(ownedNumbers); }
            public int Points { get => (int)Math.Pow(2, OwnedWinningNumbersCount - 1); }
            public int OwnedWinningNumbersCount { get => OwnedWinningNumbers.Count(); }

            public ScratchCard(string description)
            {
                Regex re = new("Card +(?<id>\\d+):( +(?<winning_number>\\d+))+ \\|( +(?<number>\\d+))+");
                var match = re.Match(description);

                id = int.Parse(match.Groups["id"].Value);
                winningNumbers = match.Groups["winning_number"].Captures.Select(c => int.Parse(c.Value));
                ownedNumbers = match.Groups["number"].Captures.Select(c => int.Parse(c.Value));
            }
        }

        public static int Exercise1()
            => File.ReadLines("Inputs/Day4.txt")
                .Select(line => new ScratchCard(line))
                .Sum(card => card.Points);

        public static int Exercise2()
        {
            var winningNumbers = File.ReadLines("Inputs/Day4.txt")
                .Select(line => new ScratchCard(line).OwnedWinningNumbersCount)
                .ToImmutableArray();

            int[] cardCount = new int[winningNumbers.Length];

            for (int i = 0; i < winningNumbers.Length; i++)
            {
                cardCount[i]++;  // Always count each card at least once.
                for (int j = 0; j < winningNumbers[i]; j++)
                {
                    cardCount[i + j + 1] += cardCount[i];
                }
            }

            return cardCount.Sum();
        }
    }
}
